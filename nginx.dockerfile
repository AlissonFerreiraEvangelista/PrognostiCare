FROM nginx:latest
LABEL maintainer="Alisson"
COPY /src/main/resources /var/www/public
COPY /nginx/app.conf /etc/nginx/nginx.conf
RUN chmod 755 -R /var/www/public
EXPOSE 80 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]

