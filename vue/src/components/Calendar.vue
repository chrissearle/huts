<script setup lang="ts">
import { useHuts } from '@/stores/useHuts'
import { onMounted } from 'vue'

const hutStore = useHuts()

onMounted(() => {
  hutStore.retrieve()
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
  </div>
</template>
