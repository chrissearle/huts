import { computed, ref } from 'vue'

import type LoginRequest from '@/types/Login'
import type Token from '@/types/Token'
import type { TokenPayload } from '@/types/Token'
import axios from 'axios'
import { defineStore } from 'pinia'
import jwt_decode from 'jwt-decode'
import { useDates } from '@/use/useDates'

const { fromSeconds, hasExpired } = useDates()

export const useAuth = defineStore(
  'authStore',
  () => {
    const tokenInternal = ref<string | undefined>(undefined)
    const rolesInternal = ref<string[]>([])
    const usernameInternal = ref<string | undefined>(undefined)
    const nameInternal = ref<string | undefined>(undefined)
    const numberInternal = ref<string | undefined>(undefined)

    const error = ref<string | undefined>(undefined)
    const exp = ref<number | undefined>(undefined)

    const resetIfExpired = () => {
      if (exp.value && hasExpired(fromSeconds(exp.value))) {
        reset()
      }
    }

    const reset = () => {
      tokenInternal.value = undefined
      rolesInternal.value = []
      usernameInternal.value = undefined
      nameInternal.value = undefined
      numberInternal.value = undefined
      error.value = undefined
      exp.value = undefined
    }

    const isAuthorized = computed<boolean>(() => {
      resetIfExpired()

      return rolesInternal.value.length > 0
    })

    const isUser = computed<boolean>(() => {
      resetIfExpired()

      return rolesInternal.value.includes('USER')
    })

    const isAdmin = computed<boolean>(() => {
      resetIfExpired()

      return rolesInternal.value.includes('ADMIN')
    })

    const login = async (user: LoginRequest) => {
      reset()

      try {
        const data = await axios.post<Token>('/api/login', {
          username: user.username,
          password: user.password
        })

        tokenInternal.value = data.data.token

        if (tokenInternal.value && tokenInternal.value.length > 0) {
          const payload = jwt_decode<TokenPayload>(tokenInternal.value)

          rolesInternal.value = payload.roles
          usernameInternal.value = payload.username
          nameInternal.value = payload.name
          numberInternal.value = payload.number
          exp.value = payload.exp
        }
      } catch (err) {
        reset()

        if (axios.isAxiosError(err)) {
          error.value = err.message
        } else {
          error.value = 'Pålogging feilet - vennligst prøv igjen'
        }
      }
    }

    const roles = computed<string[]>(() => {
      resetIfExpired()

      return rolesInternal.value
    })

    const token = computed<string | undefined>(() => {
      resetIfExpired()

      return tokenInternal.value
    })

    const username = computed<string | undefined>(() => {
      resetIfExpired()

      return usernameInternal.value
    })

    const name = computed<string | undefined>(() => {
      resetIfExpired()

      return nameInternal.value
    })

    const number = computed<string | undefined>(() => {
      resetIfExpired()

      return numberInternal.value
    })

    setInterval(() => {
      resetIfExpired()
    }, 1000)

    return {
      // Persisting store will only store things exposed here. Do not use any of the *Internal properties
      tokenInternal,
      rolesInternal,
      usernameInternal,
      nameInternal,
      numberInternal,

      exp,
      roles,
      token,
      username,
      name,
      number,
      isAuthorized,
      isUser,
      isAdmin,
      error,
      reset,
      login
    }
  },
  {
    persist: {
      debug: true
    }
  }
)

export const addTokenHeader = (options: RequestInit): RequestInit => {
  const store = useAuth()

  if (store.token) {
    options.headers = {
      ...options.headers,
      Authorization: `Bearer ${store.token}`
    }
  }

  return options
}
