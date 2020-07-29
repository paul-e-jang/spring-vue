<template>
  <div class="row bg-white">
    <div class="col-xl-2 align-self-center py-5">
      <img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid" alt="Logo" width="200" height="100"/>
    </div>
    <div class="col-xl-6 align-self-center text-center">
      <SearchBar />
    </div>
    <div class="col-xl-3 align-right align-self-center">
      <UserMenu />
    </div>
      <Categories />
      <div id="nav">
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link> |
        <router-link to="/loginpage">Login</router-link>
      </div>
      <!-- form 작성용
        <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search">
        <button class="btn btn-success my-2 my-sm-0" type="button">Search</button>
         </form>
      -->
  </div>
</template>

<style scoped lang="scss">
#nav {
  padding: 5px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #4272b9;
    }
  }
}
</style>

<script>
import SearchBar from '@/components/SearchBar.vue'
import UserMenu from '@/components/UserMenu.vue'
import Categories from '@/components/Categories.vue'
export default {
  name: 'Header',
  components: {
    SearchBar,
    UserMenu,
    Categories
  }
}
</script>
