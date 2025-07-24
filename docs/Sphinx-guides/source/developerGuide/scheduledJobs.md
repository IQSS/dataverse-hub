# Scheduled Jobs

The Dataverse Hub runs a set of cron jobs, each job is scheduled to run in a different frequency for a status check, the status check will determine if it needs to be run or not. 

For example the InstallationGitImporter is scheduled to make a check every 6 hours but the scheduledJobService will determine if the job needs to run or not, this is configure on the database on the scheduled_job table. 

This will check against the last time that the job ran succesfully which is stored in the scheduled_job_transaction_log table.

## InstallationGitImporter

This importer is what will define what installations are being monitored in the Dataverse-Hub, it will pull data from 'https://raw.githubusercontent.com/IQSS/dataverse-installations/refs/heads/main/data/data.json'.

