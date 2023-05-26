import type { ApiError, InvalidPasswordResponse } from '@/types/Responses'

import axios from 'axios'
import { useAuth } from '@/stores/useAuth'
import { useDates } from '@/use/useDates'

const { dateTimeReviver } = useDates()

export const useStoreUtils = () => {
  const jsonParseWithDates = (data: string) => {
    try {
      if (!data || data === '') {
        return {}
      }
      return JSON.parse(data, dateTimeReviver)
    } catch (err) {
      throw Error(
        `Error parsing response JSON data - ${JSON.stringify(data)} - ${JSON.stringify(err)}`
      )
    }
  }

  const buildConfig = () => {
    const authStore = useAuth()

    return {
      transformResponse: jsonParseWithDates,
      headers: {
        Accept: 'application/json',
        ...(authStore.token ? { Authorization: `Bearer ${authStore.token}` } : {})
      }
    }
  }

  const getResponse = (obj: any): string[] | undefined => {
    if (typeof obj === 'string') {
      return [obj]
    }

    if ('message' in obj) {
      return [(obj as ApiError).message]
    }

    if ('validations' in obj) {
      return (obj as InvalidPasswordResponse).validations
    }
  }

  const handleError = (error: unknown) => {
    if (axios.isAxiosError(error)) {
      const data = error.response?.data

      if (data) {
        const response = getResponse(data)

        if (response) {
          return response
        }
      }

      return [error.message]
    } else {
      console.log(`Error: ${error}`)

      return ['Noe gikk galt']
    }
  }

  return {
    buildConfig,
    handleError
  }
}
