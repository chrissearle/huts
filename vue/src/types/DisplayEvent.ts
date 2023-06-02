import type { DateTime } from 'luxon'

export interface DisplayEvent {
  id: number
  name: string
  hut: string
  start: DateTime
  end: DateTime
  classNames: string
}
