/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["/src/**/*.{html,js}"],
  mode: 'jit',
  purge: [
    './dist/**/*.html',
    './src/**/*.{js,jsx,ts,tsx,vue,css,html}',
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

