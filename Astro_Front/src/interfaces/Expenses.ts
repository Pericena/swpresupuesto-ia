export interface ExpensesData {
  labels: string[];
  datasets: {
    label: string;
    data: number[];
    backgroundColor: string[];
  }[];
}

const defaultExpensesData: ExpensesData = {
  labels: [],
  datasets: [
    {
      label: '',
      data: [],
      backgroundColor: [],
    },
  ],
};

export default defaultExpensesData;
