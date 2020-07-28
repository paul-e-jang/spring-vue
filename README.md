# Spring-vue/Marketplace
+ ## 구현 도구들(Build tools)
  + Unit test : @vue/cli - Jest
  + e2e test : @vue/cli - Nightwatch
  + ES5-6 compatibility module: @vue/cli ESlint  
  + Database : Ojdbc6(XE)
  + Front: Node.js (12.18.0)
  + Front design: (Vue.js)@vue/cli 4.4.6, BootstrapVue(4.5.1), SCSS, inline CSS
  + Back server : Embedded Tomcat on springBoot
  + Http proxy: npm - Axios
  + Communication ports: 3000(Front/Client)<->8090(Back/Server)
  + Spring Dependencies: JPA, Web, DevTools, Thymeleaf(?maybe), RESTful, Security, Transaction(?maybe), Oauth2, Social(?maybe)
  + Build: Maven 3.6.3
  + 기타(etc) dependencies : vuelidate, hibernate, .. ORM mapping 쪽은 별도 브랜치로 분류 (mybatis 등)
  + note : JQuery는 원칙적으로 쓰지 않는다.
  
+ ## Target issues
  + Ojdbc외 postgreSQL, Mysql, (+H2 DB) migration
  + Spring-boot -> Spring Legacy pjt migration
  + React.js branch
  + JSON Oauth2 --> Spring Social 로 정식 build

## bashpound: 장은학(Jang Eunhack) / eunhackjang@gmail.com
