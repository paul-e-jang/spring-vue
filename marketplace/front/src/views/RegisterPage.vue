<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="../assets/svcLogoWithoutFrames.svg">
          <div class="tagline">회원 가입</div>
        </div>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">아이디를 입력하세요.</div>
              <div class="error" v-if="!$v.form.username.alphaNum">특수문자는 입력할 수 없습니다.</div>
              <div class="error" v-if="!$v.form.username.minLength">아이디는 최소 {{$v.form.username.$params.minLength.min}} 글자입니다.</div>
              <div class="error" v-if="!$v.form.username.maxLength">아이디는 최대 {{$v.form.username.$params.maxLength.max}} 글자입니다.</div>
            </div>
          </div>
          <div class="form-group">
            <label for="emailAddress">Email address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
              <div class="error" v-if="!$v.form.emailAddress.required">이메일 주소는 필수 입니다.</div>
              <div class="error" v-if="!$v.form.emailAddress.email">유효한 이메일 주소가 아닙니다.</div>
              <div class="error" v-if="!$v.form.emailAddress.maxLength">이메일 주소는 최대 {{$v.form.emailAddress.$params.maxLength.max}} 글자입니다.</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">암호를 입력하세요.</div>
              <div class="error" v-if="!$v.form.password.minLength">암호는 최소 {{$v.form.password.$params.minLength.min}} 글자입니다.</div>
              <div class="error" v-if="!$v.form.password.maxLength">암호는 최대 {{$v.form.password.$params.maxLength.max}} 글자입니다.</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">계정 생성</button>
          <p class="accept-terms text-muted">“계정 생성”을 누르면 <a href="#">약관</a> 및 <a href="#">개인 정보 보호정책</a>에 동의합니다.</p>
          <p class="text-center text-muted">이미 계정이 있으십니까? <a href="/loginpage">로그인</a></p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'
import registrationService from '@/services/registration'

export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      registrationService.register(this.form).then(() => {
        this.$router.push({ name: 'LoginPage' })
      }).catch((error) => {
        this.errorMessage = 'Failed to register user. ' + error.message
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  max-width: 900px;
}

.register-form {
  margin-top: 50px;
  max-width: 320px;
}

.logo-wrapper {
  text-align: center;
  margin-bottom: 40px;

  .tagline {
    line-height: 180%;
    color: #666;
  }

 .logo {
    max-width: 150px;
    margin: 0 auto;
  }
}

.register-form {

  .form-group label {
    font-weight: bold;
    color: #555;
  }

  .accept-terms {
    margin: 20px 0 40px 0;
  }
}

.error{
  color: crimson;
  text-align: left;
  font-size: 14px;
}

</style>
