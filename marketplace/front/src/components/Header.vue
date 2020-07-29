<template>
  <div class="row bg-white">
    <div class="col-xl-2 align-self-center">
      <img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid" alt="Responsive image" />
    </div>
    <div class="col-xl-6 align-self-center text-center">
      <SearchBar />
    </div>
    <div class="col-xl-3 align-right align-self-center">
      <UserMenu />
    </div>

    <nav class="navbar navbar-expand-sm">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#">
            <i class="fa fa-bars" aria-hidden="true"></i>
          </button>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴1</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴2</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴3</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴4</a>
        </li>
      </ul>

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
    </nav>
  </div>
</template>

<style scoped lang="scss">
#nav {
  padding: 5px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>

<script>
import SearchBar from '@/components/SearchBar.vue'
import UserMenu from '@/components/UserMenu.vue'
export default {
  name: 'Header',
  components: {
    SearchBar,
    UserMenu
  }
}
</script>
