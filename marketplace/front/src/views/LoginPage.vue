<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <Logo class="my-4" />
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">ID or 이메일</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">이름 혹은 이메일주소를 입력하세요.</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">암호</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">암호를 입력하세요.</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">로그인</button>
          <div class="links">
            <p class="sign-up text-muted">계정이 없으십니까? <router-link to="/RegisterPage">계정 만들기</router-link></p>
            <p><a href="#">암호를 잊으신 경우</a></p>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import authenticationService from '@/services/authentication'
import Logo from '@/components/Logo.vue'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        username: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  components: {
    Logo
  },
  validations: {
    form: {
      username: {
        required
      },
      password: {
        required
      }
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      authenticationService.authenticate(this.form).then(() => {
        this.$router.push({ name: 'home' })
      }).catch((error) => {
        this.errorMessage = error.message
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.links {
  margin: 30px 0 50px 0;
  text-align: center;
}
</style>
