<template>
    <v-dialog v-model="dialog" persistent max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          icon
          dark
        >
          <v-icon> mdi-account-arrow-right </v-icon>
        </v-btn>
      </template>
    <v-sheet min-height="500">
      <div class="text-center"><img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid pa-3 my-5" alt="Logo" width="200" height="50"/></div>
      <v-sheet class="headline mb-2 text-center"> 로그인 </v-sheet>
      <v-card
      class="mx-auto px-5 ma-5"
      max-width="500"
      outlined>
    <v-form
    ref="form"
    v-model="hasError"
    lazy-validation
    class="pa-5 ma-5"
  >
    <v-text-field
      v-model="form.username"
      :counter="20"
      :rules="valid.username"
      label="아이디"
      placeholder="6글자 이상, 영문 대/소문자 및 숫자"
      required
      maxlength="20"
    ></v-text-field>
    <v-text-field
            v-model="form.password"
            :append-icon="options.passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="valid.password"
            :type="options.passwordShow ? 'text' : 'password'"
            name="input-10-1"
            label="패스워드"
            placeholder="최소 10글자, 대/소문자 구분"
            counter
            required
            class="mb-5"
            @click:append="options.passwordShow = !options.passwordShow"
          />
          <v-alert
          v-show="errorMessage"
      type="error"
      dense
      outlined
    >
      {{ errorMessage }}
    </v-alert>
  </v-form>
      </v-card>
      <v-sheet class="headline mb-2 text-center">
      <v-btn color="primary" @click.prevent="submitForm()" outlined :disabled="!form1OK" class="mr-2" >로그인</v-btn>
      <v-btn color="grey darken-2" @click="dialog=false" outlined>창닫기</v-btn>
      </v-sheet>
    </v-sheet>
    </v-dialog>
</template>

<script>
import authenticationService from '@/services/authentication'
import registrationService from '@/services/registration'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        username: '',
        password: ''
      },
      errorMessage: '',
      dialog: false,
      counter: 5,
      options: {
        passwordShow: false
      },
      valid: {
        username: [
          v => !!v || '아이디를 입력하세요.',
          v => (v && v.length <= 20 && v.length >= 6) || '아이디는 6글자 이상, 20글자 이하입니다.'
        ],
        password: [
          v => !!v || '패스워드를 입력하세요.',
          v => (v && v.length >= 10) || '패스워드는 10글자 이상입니다.'
        ]
      }
    }
  },
  methods: {
    submitForm () {
      authenticationService.authenticate(this.form).then(() => {
        authenticationService.fetchuser()
        this.$bus.$on('myDataFetched', data => {
          this.$store.commit('updateMyData', data)
        })
      }).catch((error) => {
        this.errorMessage = error.message
      })
    },
    check (param, value) {
      registrationService.checkAlready(param, value)
      this.$bus.$on('alreadyExists', data => {
        return data.paramAlreadyExists
      })
    }
  },
  computed: {
    form1OK () {
      let ok = false
      if (this.form.username && this.form.password) {
        ok = true
      }
      return ok
    }
  }
}
</script>

<style scoped>

</style>
