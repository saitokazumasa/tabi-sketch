/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        "label" : "#555",
        "frame" : "#D9D9D9",
        "sub-button" : "#5F8565",
        "interactive" : "#8DC48F",
        "link" : "#8DA3C4",
        "danger" : "#F45555"
      },
    },
  },
  plugins: [],
}

