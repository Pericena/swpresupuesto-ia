interface Product {
  name: string;
  img: string;
  precio: number;
  descuento: number;
  short_description: string;
  long_description: string;
  stock: number;
  price: number;
  discount: number;
  status: boolean;
}

const Product: Product = {
  name: '',
  img: '',
  precio: 0,
  descuento: 0,
  short_description: '',
  long_description: '',
  stock: 0,
  price: 0,
  discount: 0,
  status: true
};

export default Product;
