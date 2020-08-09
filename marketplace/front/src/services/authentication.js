import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {
  /**
   * Authenticate a login request
   * @param {Object} detail login detail
   */
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/authentications', detail).then(({ data }) => {
        resolve(data)
        console.log(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  fetchuser () {
    return new Promise((resolve, reject) => {
      axios.post('/user').then(({ data }) => {
        resolve(data)
        eventBus.$emit('myDataFetched', data)
        console.log(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  logOut () {
    return new Promise((resolve, reject) => {
      axios.post('/logout').then(({ data }) => {
        resolve(data)
        eventBus.$emit('logout', data)
        console.log(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
