<template>
  <ul class="nav float-right">
    <li v-if="Logged" class="mr-3"> {{ user }} 님, 환영합니다. </li>
    <li><router-link to="/" class="text-dark mr-3">HOME</router-link></li>
    <li><router-link to="/about" class="text-dark mr-3">ABOUT</router-link> </li>
    <li v-if="!Logged"><router-link to="/loginpage" class="text-dark mr-3">로그인</router-link> </li>
    <li v-if="!Logged"><Register /></li>
    <li v-if="Logged"><a href="api/logout" class="text-dark mr-3">로그아웃</a></li>
    <li v-if="Logged">
      <a class="text-dark mr-3" rel="nofollow" href="#" title="MyPage">
        마이페이지
      </a>
    </li>
  </ul>
</template>

<script>
import Register from '@/components/Register.vue'
export default {
  name: 'UserMenu',
  computed: {
    Logged: function () {
      return Boolean(this.$store.getters.logged)
    },
    user: function () {
      return this.$store.getters.user
    }
  },
  components: {
    Register
  }
}
</script>
