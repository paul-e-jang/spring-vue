<template>
  <ul class="nav float-right">

    <li><router-link to="/" class="text-dark mr-3"><i class="fa fa-home fa-2x" aria-hidden="true"></i></router-link></li>
    <li><router-link to="/about" class="text-dark mr-3"><i class="fa fa-info-circle fa-2x" aria-hidden="true"></i></router-link> </li>
    <li><router-link to="/loginpage" class="text-dark mr-3"><i class="fa fa-sign-in fa-2x" aria-hidden="true"></i></router-link> </li>
    <li>
      <a class="text-dark mr-3" rel="nofollow" href="#" title="MyPage">
        <i class="fa fa-user-o fa-2x"></i>
      </a>
    </li>
    <li>
      <a
        class="text-dark"
        href="https://github.com/Bashpound"
        title="Follow on GitHub"
        target="_new"
      >
        <i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
      </a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'UserMenu'
}
</script>
