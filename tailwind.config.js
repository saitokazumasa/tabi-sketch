/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/**/*.{html,js}", "./node_modules/flowbite/**/*.js"],
    theme: {
        extend: {
            colors: {
                "label" : "#555",
                "frame" : "#D9D9D9",
                "sub-button" : "#5F8565",
                "interactive" : "#8DC48F",
                "link" : "#8DA3C4",
                "link-hover" : "#6F8ECE",
                "danger" : "#F45555",
            },
            fontSize: {
                "sp-default": "0.8125rem",
                'title': '2rem',
            },
            fontFamily: {
                sans: ["M PLUS 1p", "sans-serif"],
            },
            screens: {
                'sp': '390px',
                // => @media (min-width:390px)
            }
        },
    },
    plugins: [
        require('flowbite/plugin'),
        require('tailwind-hamburgers')
    ],
}