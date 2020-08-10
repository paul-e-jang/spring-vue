<template>
  <ul class="nav float-right">
    <li class="mr-3"> {{ user }} 님, 환영합니다. authenticated: {{ this.$store.getters.authenticated }} </li>
    <li><router-link to="/" class="text-dark mr-3">HOME</router-link></li>
    <li><router-link to="/about" class="text-dark mr-3">ABOUT</router-link> </li>
    <li v-if="!Logged"><router-link to="/loginpage" class="text-dark mr-3">로그인</router-link> </li>
    <li v-if="!Logged"><router-link to="/RegisterPage" class="text-dark mr-3">회원가입</router-link></li>
    <li v-if="Logged"><a href="api/logout" class="text-dark mr-3">로그아웃</a></li>
    <li v-if="Logged">
      <a class="text-dark mr-3" rel="nofollow" href="#" title="MyPage">
        마이페이지
      </a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'UserMenu',
  data () {
  },
  computed: {
    Logged: function () {
      let judge = false
      if (this.$store.getters.authenticated === true) {
        judge = true
      }
      return judge
    },
    user: function () {
      user = this.$store.getters.user
      if (user === '') {
        user = 'GUEST'
      }
      return user
    }
  }
}
</script>
