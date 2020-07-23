# Spring-vue/Marketplace
+ ## 구현 도구들(Build tools)
  + Unit test : @vue/cli - Jest
  + e2e test : @vue/cli - Nightwatch
  + ES5-6 compatibility module: @vue/cli ESlint  
  + Database : Ojdbc6(XE)
  + Front server : Node.js (12.18.0)
  + Front design: (Vue.js)@vue/cli 4.4.6, Bootstrap(4.5.1), SCSS, inline CSS
  + Back server : Embedded Tomcat on springBoot
  + Http proxy: npm - Axios
  + Communication ports: 3000(Front)<->8090(Back)
  + Spring Dependencies: JPA, Web, DevTools, Thymeleaf(?maybe), RESTful, Security, Transaction(?maybe), Oauth2(미구현)
  + Build: Maven 3.6.3
  + 기타(etc) dependencies : vuelidate, hibernate, .. ORM mapping 쪽은 별도 브랜치로 분류 (mybatis 등)
  + note : Ojdbc외 postgreSQL, Mysql, (+H2 DB)까지 별도 브랜치 목표, .. boot가 아닌 legacy로 제작까지. React.js는 finally로. JQuery는 쓰지 않는다.
## bashpound: 장은학(Jang Eunhack) / eunhackjang@gmail.com
