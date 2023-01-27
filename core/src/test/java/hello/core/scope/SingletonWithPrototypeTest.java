package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int countInClientBean1 = clientBean1.logic();
        assertThat(countInClientBean1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int countInClientBean2 = clientBean2.logic();
        // 프로토타입 빈을 싱글톤처럼 사용하지 않으려 했지만 싱글톤처럼 사용하게 됨
        assertThat(countInClientBean2).isEqualTo(2);
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        // 직접 부를 땐 프로토타입 빈처럼 사용 결국 이도저도 아니게 사용하게 됨
        assertThat(prototypeBean.getCount()).isEqualTo(0);
    }

    static class ClientBean {

        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
