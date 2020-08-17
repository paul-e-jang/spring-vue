export default {
  updateMyData (state, data) {
    state.user.name = data.user
    state.user.authenticated = data.authenticated
    state.user.usercode = data.usercode
  },
  logout (state) {
    state.user.name = 'GUEST'
    state.user.authenticated = false
    state.user.usercode = 0
  },
  launch (state, param) {
    state.launched = param
  }

}
