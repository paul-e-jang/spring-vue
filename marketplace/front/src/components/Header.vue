<template>
  <div class="row border-bottom">
    <div class="col-xl-2 align-self-center py-3 border">
      <img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid" alt="Logo" width="200" height="100"/>
    </div>
    <div class="col-xl-6 mx-auto text-center border my-auto">
      <SearchBar />
    </div>
    <div class="col-xl-3 mx-auto my-auto border">
      <UserMenu />
    </div>
      <Categories id="categories" class="border"/>
      <div class="nav border my-auto">
      </div>
        <BreadCrumbs class="col-12 border py-0"/>
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
import BreadCrumbs from '@/components/BreadCrumbs.vue'

export default {
  name: 'Header',
  components: {
    SearchBar,
    UserMenu,
    Categories,
    BreadCrumbs
  }
}
</script>
