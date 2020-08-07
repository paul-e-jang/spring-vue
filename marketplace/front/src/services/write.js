import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * write an article
   * @param {Object} detail registration detail
   */
  DoWrite (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/write', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
