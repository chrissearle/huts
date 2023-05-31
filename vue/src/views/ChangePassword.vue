<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import { useRouter } from 'vue-router'

import { useUsers } from '@/stores/useUsers'
import { useMessages } from '@/stores/useMessages'

const router = useRouter()

const userStore = useUsers()
const messageStore = useMessages()

const pass1 = ref('')
const pass2 = ref('')
const passOld = ref('')

const passwordInvalid = computed<boolean>(
  () =>
    passOld.value === '' ||
    pass1.value === '' ||
    pass1.value !== pass2.value ||
    pass1.value.length < 8
)

const submit = async () => {
  if (!passwordInvalid.value) {
    userStore.changePassword(pass1.value, passOld.value).then(() => {
      messageStore.setMessages(['Passord byttet'])
    })
  }
}

watch(
  () => userStore.users,
  () => {
    if (!userStore.error) {
      router.push({
        name: 'future'
      })
    }
  }
)
</script>

<template>
  <div class="container">
    <div>
      <h3 class="my-4">Endre passord</h3>

      <form class="form" @submit.prevent="submit">
        <div class="row mb-3">
          <label for="passOld" class="col-sm-2 col-form-label">Gamle passord</label>
          <div class="col-sm-10">
            <input
              type="password"
              name="passOld"
              id="passOld"
              class="form-control"
              v-model="passOld"
            />
          </div>
        </div>
        <div class="row mb-3">
          <label for="pass1" class="col-sm-2 col-form-label">Nytt passord</label>
          <div class="col-sm-10">
            <input type="password" name="pass1" id="pass1" class="form-control" v-model="pass1" />
          </div>
          <div class="form-text offset-sm-2">
            Mellom 8 og 16 tegn - Minst en liten bokstav - Minst en stor bokstav - Minst et siffer
          </div>
        </div>
        <div class="row mb-3">
          <label for="pass2" class="col-sm-2 col-form-label">Gjenta nytt passord</label>
          <div class="col-sm-10">
            <input type="password" name="pass2" id="pass2" class="form-control" v-model="pass2" />
          </div>
        </div>
        <button type="submit" class="btn btn-success offset-sm-2" :disabled="passwordInvalid">
          Endre passord
        </button>
      </form>
    </div>
  </div>
</template>
