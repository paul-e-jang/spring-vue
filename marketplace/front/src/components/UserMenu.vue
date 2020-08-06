<template>
  <ul class="nav float-right">

    <li><router-link to="/" class="text-dark mr-3"><i class="fa fa-home fa-2x" aria-hidden="true"></i></router-link></li>
    <li><router-link to="/about" class="text-dark mr-3"><i class="fa fa-info-circle fa-2x" aria-hidden="true"></i></router-link> </li>
    <li><router-link to="/loginpage" class="text-dark mr-3">로그인</router-link> </li>
    <li><router-link to="/RegisterPage" class="text-dark mr-3">회원가입</router-link></li>
    <li><a href="/logout" class="text-dark mr-3">로그아웃</a></li>
    <li>
      <a class="text-dark mr-3" rel="nofollow" href="#" title="MyPage">
        마이페이지
      </a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'UserMenu'
}
</script>
