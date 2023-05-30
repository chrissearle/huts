import type Booking from '@/types/Booking'
import type { DateTime } from 'luxon'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useDates } from '@/use/useDates'
import { useStoreUtils } from '@/stores/useStoreUtils'

const { datesEqual } = useDates()
const storeUtils = useStoreUtils()

export const useBookings = defineStore(
  'bookingStore',
  () => {
    const bookings = ref<Booking[]>([])
    const error = ref<string[] | undefined>(undefined)

    const retrieve = async () => {
      try {
        const data = await axios.get<Booking[]>('/api/open/booking', storeUtils.buildConfig())

        bookings.value = data.data
      } catch (err) {
        error.value = storeUtils.handleError(err)
      }
    }

    const clear = () => (error.value = undefined)

    return {
      bookings,
      error,
      retrieve,
      clear
    }
  },
  {
    persist: false
  }
)
