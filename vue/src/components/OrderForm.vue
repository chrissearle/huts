<script setup lang="ts">
import { useHuts } from '@/stores/useHuts'
import { onMounted } from 'vue'

const hutStore = useHuts()

const submit = () => {}

onMounted(() => {
  hutStore.retrieve()
})
</script>

<template>
  <div class="container my-2">
    <h1>Bestill</h1>

    <form class="form" @submit.prevent="submit">
      <div class="row mb-3">
        <label for="username" class="col-sm-2 col-form-label">Navn</label>
        <div class="col-sm-10">
          <input name="username" id="username" class="form-control" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="fromDate" class="col-sm-2 col-form-label">Fra</label>
        <div class="col-sm-10">
          <input name="fromDate" id="fromDate" class="form-control" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="toDate" class="col-sm-2 col-form-label">Til</label>
        <div class="col-sm-10">
          <input name="toDate" id="toDate" class="form-control" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="hut" class="col-sm-2 col-form-label">Hytte</label>
        <div class="col-sm-10">
          <span v-for="(hut, idx) in hutStore.huts" :key="idx" class="me-3">
            <input type="checkbox" class="form-check-input" />
            {{ hut.name }}
          </span>
        </div>
      </div>
      <button type="submit" class="btn btn-primary offset-sm-2">Bestill</button>
    </form>
  </div>
</template>
