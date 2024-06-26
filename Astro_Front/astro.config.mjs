import { defineConfig } from 'astro/config';
import tailwind from "@astrojs/tailwind";
import react from "@astrojs/react";
import aws from 'astro-sst';

// https://astro.build/config
export default defineConfig({
  // site: 'https://pericena.github.io/',
  // base: 'presupuesto.io',
  integrations: [tailwind(), react()],
  output: "server",
  adapter: aws({
    deploymentStrategy: "edge",
  }),
});