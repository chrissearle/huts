<script setup lang="ts">
import { useDates } from '@/use/useDates'

import { useHuts } from '@/stores/useHuts'
import { useBookings } from '@/stores/useBookings'

import { computed, onMounted } from 'vue'
import { DateTime } from 'luxon'

import CalendarDisplay, { type DisplayEvent } from '@/components/CalendarDisplay.vue'

const { dateAtStartOfDay } = useDates()

const hutStore = useHuts()
const bookingStore = useBookings()

const startDate = computed<DateTime>(() => {
  if (bookingStore.bookings.length === 0) {
    return dateAtStartOfDay(DateTime.now())
  }

  return bookingStore.bookings.reduce((prev, curr) => {
    return curr.fromDate < prev.fromDate ? curr : prev
  }).fromDate
})

const displayBookings = computed<DisplayEvent[]>(() => {
  return bookingStore.bookings.map((booking) => {
    return {
      id: booking.id,
      name: booking.name,
      start: booking.fromDate,
      end: booking.toDate.plus({ days: 1 }),
      classNames: `hut hut${booking.hut.id}`
    } as DisplayEvent
  })
})

onMounted(() => {
  hutStore.retrieve()
  bookingStore.retrieve()
})
</script>

<template>
  <div class="container">
    <div v-if="hutStore.huts.length > 0" class="my-3 keys">
      <div
        v-for="(hut, hutIdx) in hutStore.huts"
        class=""
        :key="hutIdx"
        :class="'hut_start hut_end hut hut_key hut' + hut.id"
      >
        {{ hut.name }}
      </div>
    </div>

    <CalendarDisplay :bookings="displayBookings" :start-date="startDate" :key="startDate" />
  </div>
</template>

<style lang="scss">
$bgcolor1: #fcc;
$bgcolor2: #cfc;
$bgcolor3: #ccf;

.keys {
  display: flex;
}

.hut {
  margin: 3px;
  padding: 0 5px 0 5px;
  border: 3px solid;
}

.hut_key {
  margin-right: 10px;
  padding: 5px;
}

.hut1 {
  background-color: $bgcolor1;
  border-color: darken($bgcolor1, 20);
}

.hut2 {
  background-color: $bgcolor2;
  border-color: darken($bgcolor2, 20);
}

.hut3 {
  background-color: $bgcolor3;
  border-color: darken($bgcolor3, 100);
}
</style>
