export default {
  updateMyData (state, data) {
    state.user.name = data.user
    state.user.authenticated = data.authenticated
  },
  logout (state) {
    state.user.name = ''
    state.user.authenticated = false
  }
}
