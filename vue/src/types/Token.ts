export default interface Token {
  token: string
}

export interface TokenPayload {
  roles: string[]
  username: string
  name: string
  number: string
  exp: number
}
