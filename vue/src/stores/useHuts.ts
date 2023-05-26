import type { DateTime } from 'luxon'
import type Hut from '@/types/Hut'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useDates } from '@/use/useDates'
import { useStoreUtils } from '@/stores/useStoreUtils'

const { datesEqual } = useDates()
const storeUtils = useStoreUtils()

export const useHuts = defineStore(
  'hutStore',
  () => {
    const huts = ref<Hut[]>([])
    const error = ref<string[] | undefined>(undefined)

    const retrieve = async () => {
      try {
        const data = await axios.get<Hut[]>('/api/open/hut', storeUtils.buildConfig())

        huts.value = data.data
      } catch (err) {
        error.value = storeUtils.handleError(err)
      }
    }

    const clear = () => (error.value = undefined)

    return {
      huts,
      error,
      retrieve,
      clear
    }
  },
  {
    persist: false
  }
)
