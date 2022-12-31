## 未修正k8sデプロイように後でかえる
FROM nginx:alpine
RUN apt-get upgrade && apt-get install nodejs npm && npm install --global yarn && yarn && yarn build && cp build /usr/share/nginx/html
# COPY ./nginx /etc/nginx/conf.d/

CMD ["nginx", "-g", "daemon off;"]