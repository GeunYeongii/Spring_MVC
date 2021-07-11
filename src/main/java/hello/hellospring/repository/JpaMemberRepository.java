package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa는 EntityManager 라는 것으로 모든걸 동장한다.
                                    // data-jpa 라이브러리에서 이것을 자동으로 생성해줌.
                                    //우리는 이렇게 만들어진것을 Injection 해서 사용하면 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
        // jpql 이라는 쿼리 언어이다. 보통 테이블을 대상으로 쿼리를 날리지만 이것은 객체를 대상으로 쿼리를 날려
        // sql언어로 번역이 되는 것이다. Member Entity를 조회한다.
    }
}
