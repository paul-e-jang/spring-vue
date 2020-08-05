import meService from '@/services/me'

export const logout = ({ commit }) => {
  commit('logout')
}

export const getMyData = ({ commit }) => {
  meService.getMyData().then(data => {
    commit('updateMyData', data)
  })
}
