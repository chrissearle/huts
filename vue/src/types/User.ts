export default interface User {
  id: number
  username: string
  roles: string[]
  name: string | undefined
}

export interface CreateUser {
  username: string
  roles: string[]
  name: string
}
