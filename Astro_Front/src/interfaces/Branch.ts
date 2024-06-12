interface Branch {
  name: string;
  img: string;
  branch: string;
  latitude: number;
  longitude: number;
  description: string;
  address: string;
  active: boolean;
  phone: string;
}

const Branch: Branch = {
  name: '',
  img: '',
  branch: '',
  latitude: 0,
  longitude: 0,
  description: '',
  address: '',
  active: true,
  phone: ''
};

export default Branch;
