export interface EarningData {
  labels: string[];
  data: { data: number[] }[];
}

const defaultEarningData: EarningData = {
  labels: [],
  data: [],
};

export default defaultEarningData;
