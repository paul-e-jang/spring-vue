<template>
  <ul class="nav float-right">
    <li class="mr-3"> {{ this.$store.state.user.name===null? 'GUEST' : this.$store.state.user.name }} 님, 환영합니다. </li>
    <li><router-link to="/" class="text-dark mr-3">HOME</router-link></li>
    <li><router-link to="/about" class="text-dark mr-3">ABOUT</router-link> </li>
    <li v-if="notLogged"><router-link to="/loginpage" class="text-dark mr-3">로그인</router-link> </li>
    <li v-if="notLogged"><router-link to="/RegisterPage" class="text-dark mr-3">회원가입</router-link></li>
    <li v-if="!notLogged"><a href="/logout" class="text-dark mr-3">로그아웃</a></li>
    <li v-if="!notLogged">
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
  methods: {
    notLogged () {
      var judge = false
      if (this.$store.state.user.authenticate === true) {
        judge = true
      }
      return judge
    }
  }
}
</script>
