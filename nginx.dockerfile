FROM nginx:latest
LABEL maintainer="Alisson"
COPY /nginx/app.conf /etc/nginx/nginx.conf
EXPOSE 8085 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]

