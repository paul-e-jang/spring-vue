# Spring-vue/Marketplace
+ ## 프로젝트 설명(Project Header)
  + 스프링 부트 2.0/Vue.js (+ Router)로 구현한 반응형 싱글 페이지 쇼핑몰 어플리케이션
  + Oauth2(SNS로그인) 지원
  + JSON API 및 데이터 크롤링 기능 지원(예정)
  + Header / Router-view / Footer 로 직관적 페이지 구성

+ ## 개발 원칙 (Dev principles)
  + Spring MVC는 오직 파싱 대상 JSON dataset만 반환한다. (RESTful)
  + 모든 개별 페이지는 VueRouter 혹은 Layered popup으로 처리한다. (Http redirection is not allowed)
  + JQuery는 (원칙적으로) 쓰지 않는다.
  + 로고 및 아이콘 요소는 svg 만을 사용한다.
  + 모든 Framework, API는 가능하면 최신 버전을 사용한다.

+ ## 구현 도구들(Build tools)
  + Unit test : JEST
  + e2e test : Nightwatch
  + ES5-6 compatibility module: ESlint  
  + Database : Ojdbc6 XE 
  + Front: Node.js (12.18.0)
  + Front design: (Vue.js)@vue/cli 4.4.6, BootstrapVue(4.5.1), SCSS, inline CSS
  + Back server : Embedded Tomcat on springBoot
  + Http proxy: npm - Axios
  + Communication ports: 3000(Front/Client)<->8090(Back/Server)
  + Spring Dependencies: JPA, Web, DevTools, Thymeleaf(?maybe), RESTful, Security, Transaction(?maybe), Oauth2, Social(?maybe)
  + Build: Maven 3.6.3
  + 기타(etc) dependencies : vuelidate, hibernate, .. ORM mapping 쪽은 별도 브랜치로 분류 (mybatis 등)
  
+ ## Target issues
  + Ojdbc외 postgreSQL, Mysql, (+H2 DB) migration
  + Spring-boot -> Spring Legacy pjt migration
  + React.js branch
  + JSON Oauth2 --> Spring Social 로 정식 build

## bashpound: 장은학(Jang Eunhack) / eunhackjang@gmail.com
