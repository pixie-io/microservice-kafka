import base64
import json

from locust import HttpUser, TaskSet, task, constant
from random import randint, choice

class WebTasks(TaskSet):

    @task
    def load(self):

        order = {
        # 'customer.name': 'John Doe',
        # 'customer.firstname': 'John',
        # 'customer.email': 'johndoe@email.com',
        'shippingAddress.street': '333 Test St',
        'shippingAddress.zip': '94105',
        'shippingAddress.city': 'San Francisco',
        'billingAddress.street': '333 Test St',
        'billingAddress.zip': '94105',
        'billingAddress.city': 'San Francisco',
        'orderLine[0].count': '1',
        'orderLine[0].item': '3',
        'submit': ''
        }

        self.client.post("/order/", data=order)


class Web(HttpUser):

    tasks = [WebTasks]
    # wait 1 second
    wait_time = constant(1)
