# 📘 Encadrant Technique — Guide de Développement

## 🎯 Rôle de ce document

Ce document agit comme un **encadrant technique senior**.

Objectif :

* Te guider **quoi faire** (pas juste comment)
* T’aider à prendre des **bonnes décisions d’architecture**
* T’éviter les erreurs classiques en microservices

Tu peux utiliser l’IA, mais :
👉 toujours suivre ce guide comme source principale.

---

# 🧠 Mentalité à adopter

Avant tout :

❌ Ne cherche pas à finir vite
❌ Ne code pas sans comprendre
❌ Ne copie pas du code sans logique

✅ Comprends chaque brique
✅ Teste chaque étape
✅ Commit souvent

---

# 🧱 Phase 0 — Setup du projet

## Objectif

Créer une base propre du projet.

## À faire

* Créer repo Git : `music-platform`
* Créer structure :

```
frontend/
gateway/
services/
   user-service/
   music-service/
   playlist-service/
   realtime-service/
infrastructure/
docker-compose.yml
```

## Validation

✔ Repo propre
✔ Structure claire
✔ README initial

---

# ⚙️ Phase 1 — Infrastructure minimale

## Objectif

Avoir un environnement qui démarre.

## À faire

* Dockeriser :

  * postgres
  * keycloak
  * kafka
  * zookeeper

* Créer `docker-compose.yml`

## Validation

✔ Tous les services démarrent
✔ Keycloak accessible
✔ Kafka fonctionne

👉 Si ça ne marche pas → tu ne passes pas à la suite

---

# 🔐 Phase 2 — Authentification

## Objectif

Mettre en place Keycloak correctement.

## À faire

* Créer realm
* Créer client frontend
* Configurer login
* Tester récupération JWT

## Validation

✔ Login fonctionne
✔ Token JWT récupéré
✔ Token envoyé au backend

---

# 🚪 Phase 3 — API Gateway

## Objectif

Centraliser les accès backend.

## À faire

* Créer service gateway
* Configurer routes
* Ajouter validation JWT

## Validation

✔ Appel API passe par gateway
✔ Token vérifié

---

# 👤 Phase 4 — User Service

## Objectif

Premier microservice complet.

## À faire

* CRUD utilisateur
* Historique écoute
* DB PostgreSQL

## Important

👉 Ce service consomme Kafka plus tard

## Validation

✔ CRUD fonctionne
✔ DB connectée
✔ API testée

---

# 🎵 Phase 5 — Music Service

## Objectif

Gérer musique et streaming.

## À faire

* upload musique
* lire musique
* metadata

## Important

👉 Publier event Kafka :

```
SongPlayedEvent
```

## Validation

✔ upload OK
✔ lecture OK
✔ event envoyé

---

# 📡 Phase 6 — Kafka

## Objectif

Communication entre microservices.

## À faire

* Ajouter producer dans music-service
* Ajouter consumer dans user-service

## Exemple

```
Music → publish → song-played
User → consume → update history
```

## Validation

✔ Event envoyé
✔ Event consommé
✔ Données mises à jour

---

# 📂 Phase 7 — Playlist Service

## Objectif

Gérer playlists.

## À faire

* CRUD playlist
* ajout musique

## Kafka

Publier :

```
playlist-created
```

---

# ⚡ Phase 8 — Realtime Service

## Objectif

Ajouter WebSocket.

## À faire

* rooms d’écoute
* synchronisation musique

## Validation

✔ plusieurs users connectés
✔ sync fonctionne

---

# 🚀 Phase 9 — Frontend

## Objectif

Connecter tout.

## À faire

* login
* lecture musique
* playlist

## Validation

✔ login fonctionne
✔ musique joue
✔ API connectée

---

# 🐳 Phase 10 — Docker global

## Objectif

Tout lancer ensemble.

## À faire

* dockeriser chaque service
* connecter réseaux

## Validation

✔ tout fonctionne avec 1 commande

---

# 📊 Phase 11 — Amélioration

## À faire ensuite

* Redis cache
* analytics
* recommandations
* monitoring

---

# ⚠️ Règles importantes

## 1. Toujours tester avant avancer

Si une étape ne marche pas → stop.

## 2. Ne pas complexifier trop tôt

Pas de Kubernetes maintenant.

## 3. Kafka = async uniquement

Ne remplace pas REST pour tout.

## 4. Logs obligatoires

Chaque service doit log.

---

# 🧠 Comment utiliser l’IA intelligemment

Quand tu bloques :

❌ Mauvais prompt :
"code moi le service"

✅ Bon prompt :
"j’ai un music-service en spring boot, je veux ajouter un kafka producer pour song-played, voici mon code..."

---

# 🎯 Objectif final

À la fin tu dois avoir :

✔ microservices
✔ Kafka
✔ Keycloak
✔ WebSocket
✔ Docker

👉 Un projet **niveau junior++ / début mid**

---

# 🧑‍💻 Rôle de ton encadrant (moi)

Je vais :

* corriger tes choix
* simplifier quand tu compliques
* t’orienter vers les bonnes pratiques
* t’aider à débloquer rapidement

---

# 🚀 Prochaine étape

👉 Commence par :

1. créer le repo
2. faire docker-compose (postgres + keycloak + kafka)

Puis reviens me voir avec :

* ton docker-compose
* ou ton premier service

Et je te corrige comme un senior.

---
