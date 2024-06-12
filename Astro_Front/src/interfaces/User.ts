interface User {
  id: number;
  name: string;
  email: string;
  ci: number;
  address: string;
  dob: string;
  phone: number;
  gender: string;
  status: string;
  avatar: string;
}

const User: User = {
  id: 0,
  name: '',
  email: '',
  ci: 0,
  address: '',
  dob: '',
  phone: 0,
  gender: '',
  status: '',
  avatar: '',
}
export default User
