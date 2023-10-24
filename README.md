# 프로그램의 특징 
- XML 파일 안에 적혀 있는 의존성 정보를 읽고, 위상정렬 알고리즘을 사용해서 자동으로 의존성을 주입한다. 
- Front Controller 패턴에 따라 하나의 Servlet이 모든 요청을 처리한다. 

# 왜 개발했는가? 
- Java Spring의 작동원리를 이해하기 위해서이다. Java Spring은 의존성을 주입함으로써 유연한 설계를 가진 웹 애플리케이션 개발을 가능케 했다.
  
  정확히 어떤 원리를 통해 DI 를 해주는지 이해하기 위해 코드로 직접 구현했다.

- Java Spring은 단 하나의 Servlet을 가지고 있다. Dispatcher Servlet이 바로 그것이다. 모든 요청은 Dispatcher Servlet을 거쳐서 각 Controller로 전파된다.

  이를 Front Controller 패턴이라고 하는데, 이 개념을 코드로 구현하면서 정확히 그 개념을 이해하고자 하였다.
