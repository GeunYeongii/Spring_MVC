package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // <- DB 에서 ID가 자동으로 count되게끔 해줌. 이것을 Identity라고 함.
    private Long id;

    //@Column(name = "username")  DB에 username과 Mapping 시키는 것
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
