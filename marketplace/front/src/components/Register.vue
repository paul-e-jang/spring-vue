<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          text
        >
          회원가입
        </v-btn>
      </template>

    <v-stepper v-model="stage" vertical id="stepper" class="pa-5">
      <div><img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid pa-3" alt="Logo" width="200" height="50"/></div>
      <v-sheet class="headline" text> 회원 가입 </v-sheet>
      <div v-show="errorMessage" class="alert alert-danger failed my-4">에러메시지 송출될 곳{{ errorMessage }}</div>
    <v-stepper-step step="1" :complete="stage > 1">
      Terms of Service
    </v-stepper-step>

    <v-stepper-content step="1">
        <v-card-text
    class="mx-auto mb-5"
    max-width="300"
    outlined
    height="200px"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="headline text-left">약관 동의</v-list-item-title>
        <v-list-item-subtitle class="text-left">개인 정보 수집 및 이용 </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
      <div class="text--primary text-left px-3">
        수집 및 이용 정보 : 회원 아이디, 이메일 주소, 성명<br>
        이용 목적: 회원 관리<br>
        이용에 동의하시면 동의 버튼을 눌러주세요.
      </div>

  </v-card-text>
      <v-btn color="primary" @click="stage = 2" outlined class="mr-2">동의</v-btn>
      <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>비동의</v-btn>
    </v-stepper-content>

    <v-stepper-step step="2" :complete="stage > 2">ID/Password</v-stepper-step>

    <v-stepper-content step="2">
      <v-card
      class="mx-auto px-5 mb-5"
      max-width="500"
      outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="headline text-left">로그인 정보 입력</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-form
    ref="form"
    v-model="hasError"
    lazy-validation
  >
    <v-text-field
      v-model="form.username"
      ref="username"
      :counter="20"
      :rules="valid.username"
      label="아이디"
      placeholder="6글자 이상, 영문 대/소문자 및 숫자"
      required
      maxlength="20"
    ></v-text-field>

    <v-text-field
      v-model="form.emailAddress"
      ref="emailAddress"
      :rules="valid.emailAddress"
      label="E-mail"
      placeholder=" "
      required
    ></v-text-field>

    <v-text-field
            v-model="form.password"
            ref="password"
            :append-icon="form.passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="valid.password"
            :type="options.passwordShow ? 'text' : 'password'"
            name="input-10-1"
            label="패스워드"
            placeholder="최소 10글자, 대/소문자 구분"
            counter
            required
            @click:append="form.passwordShow = !form.passwordShow"
          />

    <v-text-field
            v-model="form.password2"
            ref="password2"
            :append-icon="form.passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="valid.password2"
            :type="options.passwordShow ? 'text' : 'password'"
            name="input-10-2"
            label="패스워드 확인"
            placeholder=" "
            counter
            required
            @click:append="form.passwordShow = !form.passwordShow"
          />
  </v-form>

      </v-card>

      <v-btn color="primary" @click="submit" outlined :disabled="!form1OK" class="mr-2">확인</v-btn>
      <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>취소</v-btn>
    </v-stepper-content>

    <v-stepper-step step="3" :complete="stage > 3">
      Setup Names
    </v-stepper-step>

    <v-stepper-content step="3">
 <v-card
      class="mx-auto px-6 mb-5"
      max-width="500"
      outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="headline text-left">회원 정보 입력</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-form
    ref="form2"
    v-model="hasError"
    lazy-validation
  >
    <v-text-field
      v-model="form.firstName"
      ref="firstName"
      :counter="10"
      :rules="valid.firstName"
      label="이름"
      placeholder="이름을 입력하세요 (최대 10자)"
      required
      maxlength="10"
    ></v-text-field>

    <v-text-field
      v-model="form.lastName"
      ref="lastName"
      :rules="valid.lastName"
      label="닉네임"
      :counter="10"
      placeholder="닉네임을 입력하세요 (최대 10자)"
      required
      maxlength="10"
    ></v-text-field>

  </v-form>

      </v-card>
      <v-btn color="primary" @click="stage = 2" outlined class="mr-2">이전</v-btn>
      <v-btn color="primary" @click="submit" outlined :disabled="!form2OK" class="mr-2">다음</v-btn>
      <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>취소</v-btn>
    </v-stepper-content>

    <v-stepper-step step="4" :complete="stage >= 4">Validation</v-stepper-step>

    <v-stepper-content step="4">
<v-card-text
    class="mx-auto mb-5"
    max-width="300"
    outlined
    height="200px"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="display-2">축하합니다!</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

      <div class="text--primary px-3">
        {{ form.firstName }} 님, <img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid mx-2" alt="Logo" width="100" height="50"/>
         에 가입하신 것을 진심으로 환영합니다. 이제 로그인 후 서비스를 하실 수 있습니다.<br>
         ({{ counter }}초후 자동으로 닫힙니다.)
      </div>

  </v-card-text>
      <v-btn color="grey darken-1" @click="dialog=false, stage = 1, this.$refs.form.reset()" outlined>창 닫기</v-btn>
    </v-stepper-content>
  </v-stepper>
    </v-dialog>
  </v-row>
</template>

<script>

export default {
  data () {
    return {
      dialog: false,
      stage: 1,
      counter: 5,
      form: {
        username: null,
        password: null,
        password2: null,
        emailAddress: null,
        firstName: null,
        lastName: null
      },
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
        ],
        password2: [
          v => (v === this.form.password) || '패스워드가 일치하지 않습니다.'
        ],
        emailAddress: [
          v => !!v || '이메일 주소를 입력하세요.',
          v => /.+@.+\..+/.test(v) || '이메일 주소 형식이 맞지 않습니다.'
        ],
        firstName: [
          v => !!v || '이름을 입력하세요.'
        ],
        lastName: [
          v => !!v || '닉네임을 입력하세요.'
        ]
      }
    }
  },
  computed: {
    form1OK () {
      let ok = false
      if (this.form.username && this.form.password && this.form.password2 && this.form.emailAddress) {
        ok = true
      }
      return ok
    },
    form2OK () {
      let ok = false
      if (this.form.firstName && this.form.lastName) {
        ok = true
      }
      return ok
    }
  },
  methods: {
    submit () {
      if (this.stage === 2 && this.$refs.username.validate() &&
      this.$refs.password.validate() &&
      this.$refs.password2.validate() &&
      this.$refs.emailAddress.validate()) {
        this.stage = 3
      } else if (this.stage === 3 && this.$refs.firstName.validate() && this.$refs.lastName.validate()) {
        this.stage = 4
        this.countDown()
      }
    },
    countDown () {
      if (this.counter > 0) {
        setTimeout(() => {
          this.counter--
          this.countDown()
        }, 1000)
      } if (this.counter === 0) {
        this.dialog = false
        this.stage = 1
        this.counter = 5
        this.$refs.form.reset()
        this.$refs.form2.reset()
      }
    }
  }
}

</script>

<style scoped>
#stepper{
  font-family: 'Noto Sans KR', sans-serif;
}

</style>
