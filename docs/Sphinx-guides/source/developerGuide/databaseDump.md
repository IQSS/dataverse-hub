# Database dump

For development or testing sometimes we need to have an accurate representation of the data that we have on an environment, here are a list of steps to follow to get this working:

## Generate a database dump

The first step is to generate this file, if you already have this you can jump to the next step.

The first step is to stablish a connection trough ssh for example:

```
ssh -i ./mykey.key rocky@127.0.0.1
```

Once that we are connected we can generate the image with(replace `very$4f3Pa$$`):

```
PGPASSWORD="very$4f3Pa$$" pg_dump -U postgres -F c -b -v -f /home/rocky/dv_hub_db.dump dv_hub_pg
```

We can download the image to our local environment by exiting the ssh session and running:


```
scp -i ./mykey.key rocky@127.0.0.1:/home/rocky/dv_hub_db.dump ./
```

## Restoring the file

With the postgress docker container running we can copy the file with:

```
docker cp .\dv_hub_db.dump postgres:/etc
```

And then restore with:

```
docker exec postgres pg_restore -U admin -d dv_hub_pg -v /etc/dv_hub_db.dump
```