import { DateTime } from 'luxon'
import type Hut from '@/types/Hut'

export default interface Booking {
  id: number
  name: string
  hut: Hut
  fromDate: DateTime
  toDate: DateTime
}
