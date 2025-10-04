/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}" // diz pro Tailwind analisar esses arquivos e gerar CSS só para o que você usa
  ],
  theme: {
    extend: {}, // aqui você pode adicionar cores, fontes, etc
  },
  plugins: [],
}
