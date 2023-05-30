<script setup lang="ts">
import { useHuts } from '@/stores/useHuts'
import { useBookings } from '@/stores/useBookings'

import { useDates } from '@/use/useDates'

import { onMounted } from 'vue'

const hutStore = useHuts()
const bookingStore = useBookings()

const { shortDate } = useDates()

onMounted(() => {
  hutStore.retrieve()
  bookingStore.retrieve()
})
</script>

<template>
  <div class="container">
    <h1>Kalendar</h1>

    <p v-for="(hut, index) in hutStore.huts" :key="index">
      ID: {{ hut.id }}
      <br />
      Name: {{ hut.name }}
    </p>

    <p v-for="(booking, index) in bookingStore.bookings" :key="index">
      ID: {{ booking.id }}
      <br />
      Name: {{ booking.name }}
      <br />
      Hut ID: {{ booking.hut.id }}
      <br />
      Hut Name: {{ booking.hut.name }}
      <br />
      From: {{ shortDate(booking.fromDate) }}
      <br />
      To: {{ shortDate(booking.toDate) }}
    </p>
  </div>
</template>
