export default {
  updateMyData (state, data) {
    state.user.name = data.user.name
    state.user.authenticated = true
  },
  logout (state) {
    state.user.name = ''
    state.user.authenticated = false
  }
}
